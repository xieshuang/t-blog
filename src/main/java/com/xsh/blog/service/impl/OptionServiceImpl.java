package com.xsh.blog.service.impl;

import com.xsh.blog.dao.OptionVoMapper;
import com.xsh.blog.model.Vo.OptionVo;
import com.xsh.blog.model.Vo.OptionVoExample;
import com.xsh.blog.service.IOptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * options表的service
 */
@Service
public class OptionServiceImpl implements IOptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);

    @Resource
    private OptionVoMapper optionDao;

    @Override
    public void insertOption(OptionVo optionVo) {
        LOGGER.debug("Enter insertOption method:optionVo={}", optionVo);
        optionDao.insertSelective(optionVo);
        LOGGER.debug("Exit insertOption method.");
    }

    @Override
    @Transactional
    public void insertOption(String name, String value) {
        LOGGER.debug("Enter insertOption method:name={},value={}", name, value);
        OptionVo optionVo = new OptionVo();
        optionVo.setName(name);
        optionVo.setValue(value);
        if (optionDao.selectByPrimaryKey(name) == null) {
            optionDao.insertSelective(optionVo);
        } else {
            optionDao.updateByPrimaryKeySelective(optionVo);
        }
        LOGGER.debug("Exit insertOption method.");
    }

    @Override
    @Transactional
    public void saveOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            options.forEach(this::insertOption);
        }
    }

    @Override
    public OptionVo getOptionByName(String name) {
        return optionDao.selectByPrimaryKey(name);
    }

    @Override
    public List<OptionVo> getOptions() {
        return optionDao.selectByExample(new OptionVoExample());
    }
}
