package com.first.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.first.view.MyFreeMarkerView;

import freemarker.template.TemplateException;

@Configuration
public class FreeMarkerConfig {

	@Autowired  
    protected freemarker.template.Configuration configuration;  
    @Autowired  
    protected FreeMarkerViewResolver freeMarkerResolver;  
    @Autowired  
    protected InternalResourceViewResolver internalResolver;  
    
    @PostConstruct  
    public void  setSharedVariable(){  
        configuration.setDateFormat("yyyy/MM/dd");  
  
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");  
  
        //下面三句配置的就是我自己的freemarker的自定义标签，在这里把标签注入到共享变量中去就可以在模板中直接调用了  
        //configuration.setSharedVariable("content_list", new ContentListDirective());  
        //configuration.setSharedVariable("article_list", new ArticleDirective());  
        //configuration.setSharedVariable("channel_list", new ChannelListDirective());  
          
          
        /** 
         * setting配置 
         */
        try {  
            configuration.setSetting("template_update_delay", "1");  
            configuration.setSetting("default_encoding", "UTF-8");
            configuration.setSetting("classic_compatible", "true");
            configuration.setSetting("locale", "zh_CN");
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
          
          
        /** 
         * 配置Spring JSP的视图解析器 
         */
        internalResolver.setPrefix("/XXX/");//解析前缀后XXX路径下的jsp文件</span>  
        internalResolver.setSuffix(".jsp");  
        internalResolver.setOrder(1);  
          
        /** 
         * 配置Freemarker视图解析器 
         */ 
        freeMarkerResolver.setSuffix(".ftl"); //解析后缀为html的</span>  
        freeMarkerResolver.setCache(true); //是否缓存模板</span>  
        //freeMarkerResolver.setContentType("text/html; charset=UTF-8");
        freeMarkerResolver.setRequestContextAttribute("request"); //为模板调用时，调用request对象的变量名</span>  
        freeMarkerResolver.setViewClass(MyFreeMarkerView.class);
        freeMarkerResolver.setOrder(0);  
    }
}
