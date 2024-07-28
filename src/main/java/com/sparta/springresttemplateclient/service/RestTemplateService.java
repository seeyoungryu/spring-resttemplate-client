package com.sparta.springresttemplateclient.service;

import com.sparta.springresttemplateclient.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /* build() 메서드는 RestTemplate 를 반환하고 있음
     public RestTemplate build() {
        return this.configure(new RestTemplate());
    }
     */

    public ItemDto getCallObject(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/get-call-obj")
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        /*
        UriComponentsBuilder: Spring에서 제공하는 클래스이며, URI를 구성하는 데 사용됩니다.
        fromUriString("http://localhost:7070"): 기본 URL을 설정합니다. 여기서는 로컬 서버의 7070 포트를 사용합니다.
        path("/api/server/get-call-obj"): 기본 URL에 추가 경로를 설정합니다.
        queryParam("query", query): URL에 쿼리 파라미터를 추가합니다. 여기서 query는 메서드의 입력 파라미터입니다.
        encode(): URL을 적절히 인코딩합니다.
        build(): URI를 빌드합니다.
        toUri(): URI 객체로 변환합니다.
        이 부분의 목적은 최종적으로 사용할 URI를 구성하는 것입니다. 예를 들어, query 파라미터 값이 "test"라면, 최종 URI는 다음과 같습니다:
        http://localhost:7070/api/server/get-call-obj?query=test
         */

        /*
        log.info: 로그를 출력합니다. 여기서는 구성된 URI를 로그에 출력하여 디버깅에 도움이 되도록 합니다.
         */

        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(uri, ItemDto.class);

        /*
        restTemplate.getForEntity(uri, ItemDto.class): RestTemplate을 사용하여 GET 요청을 보냅니다.
        uri: 요청을 보낼 URI입니다.
        ItemDto.class: 응답을 받을 클래스 타입입니다. 여기서는 응답이 ItemDto 객체로 변환됩니다.
        ResponseEntity<ItemDto>: 응답을 ResponseEntity 객체로 받습니다. ResponseEntity는 HTTP 응답의 상태 코드, 헤더, 본문을 포함합니다.
         */

        log.info("statusCode = " + responseEntity.getStatusCode());

        /*
        responseEntity.getStatusCode(): HTTP 응답 상태 코드를 가져옵니다.
        log.info: 응답 상태 코드를 로그에 출력합니다. 이는 응답이 성공했는지 확인하는 데 유용합니다.
         */

        return responseEntity.getBody();

        /*
        responseEntity.getBody(): 응답 본문을 가져옵니다. 이 경우, 응답 본문은 ItemDto 객체입니다.
        최종적으로 이 ItemDto 객체를 반환합니다.
         */
    }

    /*
       전체 코드 요약
    주어진 query 파라미터를 사용하여 URI를 구성합니다.
    구성된 URI를 로그에 출력합니다.
    구성된 URI로 GET 요청을 보내고 응답을 ResponseEntity<ItemDto> 객체로 받습니다.
    응답 상태 코드를 로그에 출력합니다.
    응답 본문을 ItemDto 객체로 반환합니다.
     */


    public List<ItemDto> getCallList() {
        return null;
    }

    public ItemDto postCall(String query) {
        return null;
    }

    public List<ItemDto> exchangeCall(String token) {
        return null;
    }
}

