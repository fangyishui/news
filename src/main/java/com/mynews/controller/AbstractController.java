package com.mynews.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected ModelAndView getModelAndView(String viewName){
        return new ModelAndView(viewName);
    }

//    protected SysUserEntity getUser() {
//        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
//    }

//    protected Long getUserId() {
//        return getUser().getUserId();
//    }
}
