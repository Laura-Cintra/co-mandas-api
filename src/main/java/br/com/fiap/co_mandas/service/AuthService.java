package br.com.fiap.co_mandas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.co_mandas.repository.UserRepository;

// interface --> contrato, pode ser UserDetailsService mas precisa atender a regra da implementação do método
// Ensino pro spring onde ele encontra detalhes do usuário
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("usuário não encontrado")
        );
    }
    
}
