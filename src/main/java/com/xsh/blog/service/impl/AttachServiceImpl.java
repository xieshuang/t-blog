package com.xsh.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsh.blog.dao.AttachMapper;
import com.xsh.blog.model.Vo.AttachVo;
import com.xsh.blog.model.entity.Attach;
import com.xsh.blog.service.IAttachService;
import com.xsh.blog.utils.DateKit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachMapper, Attach> implements IAttachService {

    @Resource
    private AttachMapper attachMapper;

    @Override
    public PageInfo<AttachVo> getAttachs(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        QueryWrapper<Attach> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Attach> attaches = attachMapper.selectList(queryWrapper);
        return new PageInfo<>(BeanUtil.copyToList(attaches, AttachVo.class));
    }

    @Override
    public AttachVo selectById(Integer id) {
        if (null != id) {
            Attach attach = attachMapper.selectById(id);
            return BeanUtil.copyProperties(attach, AttachVo.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void save(String fname, String fkey, String ftype, Integer author) {
        Attach attach = new Attach();
        attach.setFname(fname);
        attach.setAuthorId(author);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachMapper.insert(attach);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (null != id) {
            attachMapper.deleteById(id);
        }
    }
}
