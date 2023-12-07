package com.github.tqiIsabela.systemPix.serviceImpl;

import com.github.tqiIsabela.systemPix.dto.PixKeyRequest;
import com.github.tqiIsabela.systemPix.exception.PixKeyNotFoundException;
import com.github.tqiIsabela.systemPix.model.PixKey;
import com.github.tqiIsabela.systemPix.repository.PixKeyRepository;
import com.github.tqiIsabela.systemPix.service.serviceImpl.PixKeyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PixKeyServiceImplTest {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @InjectMocks
    private PixKeyServiceImpl pixKeyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePixKey() {
        PixKeyRequest pixKeyRequest = new PixKeyRequest();
        pixKeyRequest.setCpf(123456789L);
        pixKeyRequest.setEmail("test@example.com");
        pixKeyRequest.setPhoneNumber("1234567890");
        pixKeyRequest.setAccountId(123852L);

        when(pixKeyRepository.save(any())).thenReturn(new PixKey());

        String randomKey = pixKeyService.createPixKey(pixKeyRequest);

        assertNotNull(randomKey);
        verify(pixKeyRepository, times(1)).save(any());
    }

    @Test
    void testFindByCpf() {
        Long cpf = 123456789L;
        when(pixKeyRepository.findByCpf(cpf)).thenReturn(Optional.of(new PixKey()));

        Optional<PixKey> result = pixKeyService.findByCpf(cpf);

        assertTrue(result.isPresent());
        verify(pixKeyRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testFindByCpfNotFound() {
        Long cpf = 123456789L;
        when(pixKeyRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<PixKey> result = pixKeyService.findByCpf(cpf);

        assertTrue(result.isEmpty());
        verify(pixKeyRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testUpdatePixKey() {
        PixKeyRequest pixKeyRequest = new PixKeyRequest();
        pixKeyRequest.setCpf(123456789L);
        pixKeyRequest.setEmail("test@example.com");
        pixKeyRequest.setPhoneNumber("1234567890");
        pixKeyRequest.setAccountId(123852L);

        when(pixKeyRepository.findById(pixKeyRequest.getCpf())).thenReturn(Optional.of(new PixKey()));

        assertDoesNotThrow(() -> pixKeyService.updatePixKey(pixKeyRequest));
        verify(pixKeyRepository, times(1)).findById(pixKeyRequest.getCpf());
        verify(pixKeyRepository, times(1)).save(any());
    }

    @Test
    void testUpdatePixKeyNotFound() {
        PixKeyRequest pixKeyRequest = new PixKeyRequest();
        pixKeyRequest.setCpf(123456789L);
        pixKeyRequest.setEmail("test@example.com");
        pixKeyRequest.setPhoneNumber("1234567890");
        pixKeyRequest.setAccountId(123852L);

        when(pixKeyRepository.findById(pixKeyRequest.getCpf())).thenReturn(Optional.empty());

        PixKeyNotFoundException exception = assertThrows(PixKeyNotFoundException.class,
                () -> pixKeyService.updatePixKey(pixKeyRequest));

        assertEquals("Chave PIX não encontrada", exception.getMessage());
        verify(pixKeyRepository, times(1)).findById(pixKeyRequest.getCpf());
        verify(pixKeyRepository, never()).save(any());
    }

    @Test
    public void testDeletePixKey() {
        pixKeyService.deletePixKey(123L);

        verify(pixKeyRepository, times(1)).deleteById(123L);
    }

    @Test
    public void testDeletePixKeyNotFound() {
        assertThrows(PixKeyNotFoundException.class, () -> pixKeyService.deletePixKey(456L));
        verify(pixKeyRepository, never()).deleteById(456L);
    }


}



