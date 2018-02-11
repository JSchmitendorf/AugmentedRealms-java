package io.augmentedrealms.client.service;

import io.augmentedrealms.common.database.model.Token;
import io.augmentedrealms.common.database.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private TokenRepository tokenRepository;

    public ResourceService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public List<Token> getAvailableTokens() {
        return tokenRepository.findAll();
    }
}
