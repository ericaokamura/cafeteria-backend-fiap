package io.fiap.erp.service;

import io.fiap.erp.model.AuditoriaLog;
import io.fiap.erp.model.DadosAutenticacao;
import io.fiap.erp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void salvarLog(AuditoriaLog log) {
        this.logRepository.save(log);
    }

    public List<AuditoriaLog> retornarLogPorMensagemOrdenarPorDataHoraAuditoriaDesc(String mensagemLog) {
        return this.logRepository.findByMensagemLogContainingOrderByDataHoraAuditoriaDesc(mensagemLog);
    }
}
