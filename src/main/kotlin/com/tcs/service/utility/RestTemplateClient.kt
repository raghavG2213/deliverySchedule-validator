package com.tcs.service.utility

import com.tcs.service.model.ServiceResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger


@Component
class RestTemplateClient {

    @Autowired
    lateinit var restTemplate: RestTemplate

//    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Send POST requests and submit parameters through Form forms
     */
    fun <T> postForm(params: Map<String, String>, url: String, t:ServiceResponse) : ServiceResponse? {
//        logger.info("request URL===========:{}", url)
//        logger.info("Request parameters===========:{}", params)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        val requestParams = LinkedMultiValueMap<String, String>()
        params.forEach(requestParams::add)
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(requestParams, httpHeaders)

        return restTemplate.postForObject(url, httpEntity, ServiceResponse::class.java)
    }
}