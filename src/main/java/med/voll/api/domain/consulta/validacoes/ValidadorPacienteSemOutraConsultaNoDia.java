package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var segundoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween((dados.idPaciente()), primeiroHorario, segundoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possuí uma consulta agendada nesse dia");
        }
    }
}