package cc.tinker.controller;

import cc.tinker.entity.SiteEncodePasswordEntity;
import cc.tinker.services.SiteEncodeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tinker.entr.entity.FrontEndResponse;
import tinker.entr.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Tinker on 2017/4/10.
 */
@RestController
@RequestMapping("/passwordMgr")
public class PassWordMgrController {

    @Autowired
    SiteEncodeService siteEncodeService;

    /**
     * 创建、修改用户密码记录；
     *
     * @param siteEncode
     * @return
     */
    @RequestMapping("/encryptPsw")
    public FrontEndResponse encryptPsw(SiteEncodePasswordEntity siteEncode) {
        siteEncodeService.encodeAndSaveData(siteEncode);
        return new FrontEndResponse(true);
    }

    /**
     * 删除用户密码记录
     */
    @RequestMapping("/deletePsw")
    public FrontEndResponse deleteSiteEncode(SiteEncodePasswordEntity siteEncode) {

        return new FrontEndResponse(true);
    }

    /**
     * 获取用户账户密码列表，根据不同的参数
     */
    @RequestMapping("/getSiteBootstrapTable")
    public String getSiteBootstrapTable(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "sortType", defaultValue = "auto") String sortType,
                                        HttpServletRequest request) {

        Map<String, Object> conditions = ServletUtils.getParametersStartingWith(request, "condition_");
        Page<SiteEncodePasswordEntity> entityPage = siteEncodeService.getSiteBootstrapTable(conditions, pageNumber, pageSize, sortType);
        return new Gson().toJson(entityPage);
    }


}
