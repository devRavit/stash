package com.ravit.stash.configuration

import com.ravit.stash.property.WebProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableConfigurationProperties(WebProperties::class)
class WebConfiguration(
    private val webProperties: WebProperties,
) : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins(*webProperties.cors.allowedOrigins.toTypedArray())
            .allowedMethods(
                RequestMethod.GET.name,
                RequestMethod.POST.name,
                RequestMethod.PUT.name,
                RequestMethod.DELETE.name,
                RequestMethod.PATCH.name,
                RequestMethod.OPTIONS.name,
                RequestMethod.HEAD.name,
            ).allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600)
    }
}
