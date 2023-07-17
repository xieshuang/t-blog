package com.xsh.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xsh.blog.controller.BaseController;
import com.xsh.blog.model.Bo.RestResponseBo;
import com.xsh.blog.model.Vo.CommentVo;
import com.xsh.blog.model.Vo.CommentVoExample;
import com.xsh.blog.model.Vo.UserVo;
import com.xsh.blog.model.entity.Comments;
import com.xsh.blog.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 */
@Controller
@RequestMapping("admin/comments")
@Slf4j
public class CommentController extends BaseController {

    @Resource
    private ICommentService commentsService;

    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        UserVo users = this.user(request);
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author", users.getUid());
        queryWrapper.orderByDesc("coid");
        PageInfo<CommentVo> commentsPaginator = commentsService.getCommentsWithPage(queryWrapper, page, limit);
        request.setAttribute("comments", commentsPaginator);
        return "admin/comment_list";
    }

    /**
     * 删除一条评论
     *
     * @param coid
     * @return
     */
    @PostMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam Integer coid) {
        try {
            CommentVo comments = commentsService.getCommentById(coid);
            if (null == comments) {
                return RestResponseBo.fail("不存在该评论");
            }
            commentsService.delete(coid, comments.getCid());
        } catch (Exception e) {
            String msg = "评论删除失败";
            log.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "status")
    @ResponseBody
    public RestResponseBo delete(@RequestParam Integer coid, @RequestParam String status) {
        try {
            CommentVo comments = commentsService.getCommentById(coid);
            if (comments != null) {
                comments.setCoid(coid);
                comments.setStatus(status);
                commentsService.update(comments);
            } else {
                return RestResponseBo.fail("操作失败");
            }
        } catch (Exception e) {
            String msg = "操作失败";
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
