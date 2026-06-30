import { useState } from 'react';
import api from '../api/api';

export default function PacienteForm({ onPacienteCriado }) {
  const [form, setForm] = useState({
    nome: '',
    dataNascimento: '',
    cpf: '',
    telefone: '',
    tipo: 'ADULTO',
  });
  const [erro, setErro] = useState(null);

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setErro(null);
    try {
      await api.post('/pacientes', form);
      setForm({ nome: '', dataNascimento: '', cpf: '', telefone: '', tipo: 'ADULTO' });
      onPacienteCriado();
    } catch (err) {
      const msg = err.response?.data?.mensagem
        || Object.values(err.response?.data || {}).join(', ')
        || 'Erro ao cadastrar paciente';
      setErro(msg);
    }
  }

  return (
    <form onSubmit={handleSubmit} className="card">
      <h3>Cadastrar Paciente</h3>
      {erro && <p className="erro">{erro}</p>}
      <input
        name="nome"
        placeholder="Nome completo"
        value={form.nome}
        onChange={handleChange}
        required
      />
      <input
        type="date"
        name="dataNascimento"
        value={form.dataNascimento}
        onChange={handleChange}
        required
      />
      <input
        name="cpf"
        placeholder="CPF"
        value={form.cpf}
        onChange={handleChange}
        required
      />
      <input
        name="telefone"
        placeholder="Telefone"
        value={form.telefone}
        onChange={handleChange}
      />
      <select name="tipo" value={form.tipo} onChange={handleChange}>
        <option value="ADULTO">Adulto</option>
        <option value="CRIANCA">Criança</option>
      </select>
      <button type="submit">Cadastrar</button>
    </form>
  );
}