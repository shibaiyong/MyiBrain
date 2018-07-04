package com.uec.ibrain.ibrain.menu;

import com.uec.ibrain.ibrain.common.BaseController;
import com.uec.ibrain.ibrain.menu.bean.Menu;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uec.ibrain.ibrain.common.Constant;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: 主页的服务可以自主选择</p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/5/2</p>
 */

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    @RequestMapping(value = "/myservice", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getMyService(){

        List<Menu> list = new ArrayList<>();

        for (int i=0; i < Constant.MENUS.length ; i++){
            Menu menu = new Menu();

            menu.setId(i+1+"");
            menu.setName(Constant.MENUS[i]);
            menu.setClickUrl(Constant.MENUURLS[i]);
            menu.setImgUrl(Constant.MENUIMGS[i]);
            menu.setFlag(Constant.MENUFLAGS[i]);

            list.add(menu);
        }

        return list;
    }

}
