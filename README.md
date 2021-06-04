# Log-helper-demo

## 项目介绍

`Log-helper-demo`是一系列使用`Log-helper`插件的示例项目。

`Log-helper`是一个轻量的接口级日志框架，便于节约用户自己设计日志处理器的工作成本。

<br>

### log-helper-demo项目源码

| Github                                     | Gitee                                     |
| ------------------------------------------ | ----------------------------------------- |
| https://github.com/jyannis/log-helper-demo | https://gitee.com/jyannis/log-helper-demo |

<br>

### log-helper项目源码

| Github                                | Gitee                                |
| ------------------------------------- | ------------------------------------ |
| https://github.com/jyannis/log-helper | https://gitee.com/jyannis/log-helper |

<br>

### 联系方式

|  QQ  |   123400197    |
| :--: | :------------: |
|  WX  |   jyannis123   |
| Mail | jyannis@qq.com |

<br>

<br>

## 分支介绍

建议按序阅读。

| BRANCH            | DESCRIPTION                                         |
| ----------------- | --------------------------------------------------- |
| default           | 简单使用`log-helper`，无任何额外配置。              |
| path-match        | URL过滤接口，为项目提供细粒度定制化的日志处理能力。 |
| custom-logHandler | 自定义日志处理器，根据业务需要调整日志处理方式。    |
| custom-logInfo    | 自定义日志数据实体，补充业务需要的其他日志信息。    |

<br>

<br>

## 其他补充

为了便于用户进行接口访问测试，本项目接入了`swagger2`接口文档配置。

本地启动项目，在浏览器访问http://localhost:8080/swagger-ui.html即可获取接口信息，

点击任何接口 → 点击`Try it out` → 输入相关参数 → 点击`Execute`即可进行访问测试。