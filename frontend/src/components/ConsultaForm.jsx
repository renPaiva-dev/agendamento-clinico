import { useEffect, useState } from 'react';
import api from '../api/api';

export default function ConsultaForm({ onConsultaCriada }) {
  const [pacientes, setPacientes] = useState([]);
  const [form, setForm] = useState({
    pacienteId: '',
    dataHora: '',
    tipoConsulta: '',
    observacoes: '',
  });
  const [erro, setErro] = useState(null);

  useEffect(() => {
    api.get('/pacientes').then((res) => setPacientes(res.data));
  }, []);

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setErro(null);
    try {
      await api.post('/consultas', {
        ...form,
        pacienteId: Number(form.pacienteId),
      });
      setForm({ pacienteId: '', dataHora: '', tipoConsulta: '', observacoes: '' });
      onConsultaCriada();
    } catch (err) {
      setErro(err.response?.data?.mensagem || 'Erro ao agendar consulta');
    }
  }

  return (
    <form onSubmit={handleSubmit} className="card">
      <h3>Agendar Consulta</h3>
      {erro && <p className="erro">{erro}</p>}
      <select name="pacienteId" value={form.pacienteId} onChange={handleChange} required>
        <option value="">Selecione o paciente</option>
        {pacientes.map((p) => (
          <option key={p.id} value={p.id}>{p.nome}</option>
        ))}
      </select>
      <input
        type="datetime-local"
        name="dataHora"
        value={form.dataHora}
        onChange={handleChange}
        required
      />
      <input
        name="tipoConsulta"
        placeholder="Tipo (ex: Rotina, Puericultura)"
        value={form.tipoConsulta}
        onChange={handleChange}
        required
      />
      <input
        name="observacoes"
        placeholder="Observações (opcional)"
        value={form.observacoes}
        onChange={handleChange}
      />
      <button type="submit">Agendar</button>
    </form>
  );
}