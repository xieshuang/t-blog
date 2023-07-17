package com.xsh.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsh.blog.constant.WebConst;
import com.xsh.blog.dao.CommentsMapper;
import com.xsh.blog.exception.BusinessException;
import com.xsh.blog.model.Bo.CommentBo;
import com.xsh.blog.model.Vo.CommentVo;
import com.xsh.blog.model.Vo.CommentVoExample;
import com.xsh.blog.model.Vo.ContentVo;
import com.xsh.blog.model.entity.Comments;
import com.xsh.blog.service.ICommentService;
import com.xsh.blog.service.IContentService;
import com.xsh.blog.utils.DateKit;
import com.xsh.blog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private IContentService contentService;

    @Override
    @Transactional
    public String insertComment(CommentVo comments) {
        if (null == comments) {
            return "评论对象为空";
        }
        if (StringUtils.isBlank(comments.getAuthor())) {
            comments.setAuthor("热心网友");
        }
        if (StringUtils.isNotBlank(comments.getMail()) && !TaleUtils.isEmail(comments.getMail())) {
            return "请输入正确的邮箱格式";
        }
        if (StringUtils.isBlank(comments.getContent())) {
            return "评论内容不能为空";
        }
        if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
            return "评论字数在5-2000个字符";
        }
        if (null == comments.getCid()) {
            return "评论文章不能为空";
        }
        ContentVo contents = contentService.getContents(String.valueOf(comments.getCid()));
        if (null == contents) {
            return "不存在的文章";
        }
        comments.setOwnerId(contents.getAuthorId());
        comments.setStatus("not_audit");
        comments.setCreated(DateKit.getCurrentUnixTime());
        commentsMapper.insert(BeanUtil.copyProperties(comments, Comments.class));

        ContentVo temp = new ContentVo();
        temp.setCid(contents.getCid());
        temp.setCommentsNum(contents.getCommentsNum() + 1);
        contentService.updateContentByCid(temp);

        return WebConst.SUCCESS_RESULT;
    }

    @Override
    public PageInfo<CommentBo> getComments(Integer cid, int page, int limit) {

        if (null != cid) {
            PageHelper.startPage(page, limit);
            CommentVoExample commentVoExample = new CommentVoExample();
            commentVoExample.createCriteria().andCidEqualTo(cid).andParentEqualTo(0).andStatusIsNotNull().andStatusEqualTo("approved");
            commentVoExample.setOrderByClause("coid desc");
            QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cid", cid);
            queryWrapper.eq("parent", 0);
            queryWrapper.eq("status", "approved");
            List<Comments> parents = commentsMapper.selectList(queryWrapper);
            List<CommentVo> datas = BeanUtil.copyToList(parents, CommentVo.class);
            PageInfo<CommentVo> commentPaginator = new PageInfo<>(datas);
            PageInfo<CommentBo> returnBo = copyPageInfo(commentPaginator);
            if (datas.size() != 0) {
                List<CommentBo> comments = new ArrayList<>(datas.size());
                datas.forEach(parent -> {
                    CommentBo comment = new CommentBo(parent);
                    comments.add(comment);
                });
                returnBo.setList(comments);
            }
            return returnBo;
        }
        return null;
    }

    @Override
    public PageInfo<CommentVo> getCommentsWithPage(QueryWrapper<Comments> queryWrapper, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Comments> commentVos = commentsMapper.selectList(queryWrapper);
        PageInfo<CommentVo> pageInfo = new PageInfo<>(BeanUtil.copyToList(commentVos, CommentVo.class));
        return pageInfo;
    }

    @Override
    @Transactional
    public void update(CommentVo comments) {
        if (null != comments && null != comments.getCoid()) {
            Comments comment = BeanUtil.copyProperties(comments, Comments.class);
            commentsMapper.updateById(comment);
        }
    }

    @Override
    @Transactional
    public void delete(Integer coid, Integer cid) {
        if (null == coid) {
            throw new BusinessException("主键为空");
        }
        commentsMapper.deleteById(coid);
        ContentVo contents = contentService.getContents(cid + "");
        if (null != contents && contents.getCommentsNum() > 0) {
            ContentVo temp = new ContentVo();
            temp.setCid(cid);
            temp.setCommentsNum(contents.getCommentsNum() - 1);
            contentService.updateContentByCid(temp);
        }
    }

    @Override
    public CommentVo getCommentById(Integer coid) {
        if (null != coid) {
            return BeanUtil.copyProperties(commentsMapper.selectById(coid),CommentVo.class);
        }
        return null;
    }

    /**
     * copy原有的分页信息，除数据
     *
     * @param ordinal
     * @param <T>
     * @return
     */
    private <T> PageInfo<T> copyPageInfo(PageInfo ordinal) {
        PageInfo<T> returnBo = new PageInfo<T>();
        returnBo.setPageSize(ordinal.getPageSize());
        returnBo.setPageNum(ordinal.getPageNum());
        returnBo.setEndRow(ordinal.getEndRow());
        returnBo.setTotal(ordinal.getTotal());
        returnBo.setHasNextPage(ordinal.isHasNextPage());
        returnBo.setHasPreviousPage(ordinal.isHasPreviousPage());
        returnBo.setIsFirstPage(ordinal.isIsFirstPage());
        returnBo.setIsLastPage(ordinal.isIsLastPage());
        returnBo.setNavigateFirstPage(ordinal.getNavigateFirstPage());
        returnBo.setNavigateLastPage(ordinal.getNavigateLastPage());
        returnBo.setNavigatepageNums(ordinal.getNavigatepageNums());
        returnBo.setSize(ordinal.getSize());
        returnBo.setPrePage(ordinal.getPrePage());
        returnBo.setNextPage(ordinal.getNextPage());
        return returnBo;
    }
}
