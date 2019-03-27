/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.xkcoding.scaffold.codegen.model.payload.GenConfigRequest;
import com.xkcoding.scaffold.codegen.model.payload.TablePageRequest;
import com.xkcoding.scaffold.codegen.service.ScaffoldCodegenService;
import com.xkcoding.scaffold.common.api.R;
import com.xkcoding.scaffold.launcher.constants.AppConstant;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/scaffold/codegen")
@Profile({AppConstant.DEV_CODE, AppConstant.TEST_CODE})
public class ScaffoldCodegenController {
    private final ScaffoldCodegenService scaffoldCodeGenService;

    /**
     * 列表
     *
     * @param request 参数集
     * @return 数据库表
     */
    @GetMapping("/table")
    public R listTables(TablePageRequest request) {
        return R.success(scaffoldCodeGenService.listTables(request));
    }

    /**
     * 生成代码
     */
    @SneakyThrows
    @PostMapping("")
    public void generatorCode(@RequestBody GenConfigRequest genConfig, HttpServletResponse response) {
        byte[] data = scaffoldCodeGenService.generatorCode(genConfig);

        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }
}
