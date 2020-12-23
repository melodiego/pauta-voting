package io.sicredi.pautavoting.api.service;

import feign.FeignException;
import io.sicredi.pautavoting.api.exception.RecordNotFoundException;
import io.sicredi.pautavoting.api.integration.UserInfoClient;
import io.sicredi.pautavoting.api.integration.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class UserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);
    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final UserInfoClient userInfoClient;

    public UserInfoService(UserInfoClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }

    public UserInfoDTO getByCpf(String cpf) {
        LOGGER.debug(" Verificar se existe usuário com cpf informado {} na api de user-info", HtmlUtils.htmlEscape(cpf));
        try {
            return userInfoClient.getByCpf(cpf).getBody();
        } catch (FeignException ex) {
            LOGGER.error("Erro ao chamar a api", ex);
            if(ex.status() == HttpStatus.NOT_FOUND.value()){
                throw new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + cpf);
            }
            throw ex;
        }
    }
}
