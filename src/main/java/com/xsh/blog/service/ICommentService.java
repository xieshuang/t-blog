package com.xsh.blog.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xsh.blog.model.Bo.CommentBo;
import com.xsh.blog.model.Vo.CommentVo;
import com.xsh.blog.model.entity.Comments;

/**
 */
public interface ICommentService extends IService<Comments> {

    /**
     * 保存对象
     * @param commentVo
     */
    String insertComment(CommentVo commentVo);

    /**
     * 获取文章下的评论
     * @param cid
     * @param page
     * @param limit
     * @return CommentBo
     */
    PageInfo<CommentBo> getComments(Integer cid, int page, int limit);

    /**
     * 获取文章下的评论
     * @param queryWrapper
     * @param page
     * @param limit
     * @return CommentVo
     */
    PageInfo<CommentVo> getCommentsWithPage(QueryWrapper<Comments> queryWrapper, int page, int limit);


    /**
     * 根据主键查询评论
     * @param coid
     * @return
     */
    CommentVo getCommentById(Integer coid);


    /**
     * 删除评论，暂时没用
     * @param coid
     * @param cid
     * @throws Exception
     */
    void delete(Integer coid, Integer cid);

    /**
     * 更新评论状态
     * @param comments
     */
    void update(CommentVo comments);

}
