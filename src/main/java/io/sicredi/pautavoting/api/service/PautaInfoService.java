package io.sicredi.pautavoting.api.service;

import feign.FeignException;
import io.sicredi.pautavoting.api.exception.RecordNotFoundException;
import io.sicredi.pautavoting.api.integration.PautaInfoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class PautaInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PautaInfoService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PautaInfoClient pautaInfoClient;

    public PautaInfoService(PautaInfoClient pautaInfoClient) {
        this.pautaInfoClient = pautaInfoClient;
    }

    public Boolean existsPautaById(String id) {
        LOGGER.debug(" Verificar se existe pauta criada com ID informado {} na api de pauta-info", HtmlUtils.htmlEscape(id));
        try {
            return pautaInfoClient.existsPautaById(id).getBody();
        } catch (FeignException ex) {
            LOGGER.error("Erro ao chamar a api", ex);
            if(ex.status() == HttpStatus.NOT_FOUND.value()){
                throw new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id);
            }
            throw ex;
        }
    }
}