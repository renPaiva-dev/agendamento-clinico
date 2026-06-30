import { useEffect, useState } from 'react';
import api from '../api/api';

export default function PacienteLista({ refresh }) {
  const [pacientes, setPacientes] = useState([]);

  useEffect(() => { carregar(); }, [refresh]);

  async function carregar() {
    const res = await api.get('/pacientes');
    setPacientes(res.data);
  }

  async function deletar(id) {
    if (!confirm('Excluir paciente?')) return;
    await api.delete(`/pacientes/${id}`);
    carregar();
  }

  return (
    <div className="card">
      <h3>Pacientes cadastrados</h3>
      {pacientes.length === 0 && <p className="empty-state">Nenhum paciente cadastrado ainda.</p>}
      <ul className="patient-list">
        {pacientes.map((p) => (
          <li key={p.id} className="patient-row">
            <div>
              <div className="patient-name">{p.nome}</div>
              <div className="patient-meta">CPF {p.cpf}</div>
            </div>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.6rem' }}>
              <span className={`tipo-pill ${p.tipo === 'CRIANCA' ? 'crianca' : ''}`}>
                {p.tipo === 'CRIANCA' ? 'Criança' : 'Adulto'}
              </span>
              <button className="ghost" onClick={() => deletar(p.id)}>Excluir</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}