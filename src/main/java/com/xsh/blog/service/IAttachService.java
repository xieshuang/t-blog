package com.xsh.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xsh.blog.model.Vo.AttachVo;
import com.xsh.blog.model.entity.Attach;

/**
 */
public interface IAttachService extends IService<Attach> {
    /**
     * 分页查询附件
     * @param page
     * @param limit
     * @return
     */
    PageInfo<AttachVo> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     *
     * @param fname
     * @param fkey
     * @param ftype
     * @param author
     */
    void save(String fname, String fkey, String ftype, Integer author);

    /**
     * 根据附件id查询附件
     * @param id
     * @return
     */
    AttachVo selectById(Integer id);

    /**
     * 删除附件
     * @param id
     */
    void deleteById(Integer id);
}
