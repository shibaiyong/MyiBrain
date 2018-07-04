package com.uec.ibrain.ibrain.home.controller;

import com.uec.ibrain.ibrain.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: </p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/18</p>
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping("/homePage")
    public String myServicePage(){
        return "frontend/myservice/myservice";
    }

}
