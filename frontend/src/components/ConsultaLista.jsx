import { useEffect, useState } from 'react';
import api from '../api/api';

const FILTROS = [
  { label: 'Todas', value: '' },
  { label: 'Agendadas', value: 'AGENDADA' },
  { label: 'Realizadas', value: 'REALIZADA' },
  { label: 'Canceladas', value: 'CANCELADA' },
];

export default function ConsultaLista({ refresh }) {
  const [consultas, setConsultas] = useState([]);
  const [filtroStatus, setFiltroStatus] = useState('');

  useEffect(() => { carregar(); }, [refresh, filtroStatus]);

  async function carregar() {
    const url = filtroStatus ? `/consultas?status=${filtroStatus}` : '/consultas';
    const res = await api.get(url);
    setConsultas(res.data);
  }

  async function atualizarStatus(id, status) {
    await api.patch(`/consultas/${id}/status?status=${status}`);
    carregar();
  }

  return (
    <div className="card">
      <h3>Linha do tempo</h3>

      <div className="timeline-filter">
        {FILTROS.map((f) => (
          <button
            key={f.value}
            className={filtroStatus === f.value ? 'active' : ''}
            onClick={() => setFiltroStatus(f.value)}
          >
            {f.label}
          </button>
        ))}
      </div>

      {consultas.length === 0 && <p className="empty-state">Nenhuma consulta nesse filtro.</p>}

      <div className="timeline">
        {consultas.map((c) => (
          <div className="timeline-item" key={c.id}>
            <span className={`timeline-dot ${c.status}`} />
            <div className="timeline-time">
              {new Date(c.dataHora).toLocaleString('pt-BR', {
                day: '2-digit', month: '2-digit', hour: '2-digit', minute: '2-digit',
              })}
            </div>
            <div className="timeline-title">{c.paciente.nome}</div>
            <div className="timeline-sub">
              {c.tipoConsulta} · <span className={`status-tag ${c.status}`}>{c.status}</span>
            </div>
            {c.status === 'AGENDADA' && (
              <div className="timeline-actions">
                <button className="ghost" onClick={() => atualizarStatus(c.id, 'REALIZADA')}>Marcar realizada</button>
                <button className="ghost danger-ghost" onClick={() => atualizarStatus(c.id, 'CANCELADA')}>Cancelar</button>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}