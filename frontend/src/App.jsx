import { useState } from 'react';
import PacienteForm from './components/PacienteForm';
import PacienteLista from './components/PacienteLista';
import ConsultaForm from './components/ConsultaForm';
import ConsultaLista from './components/ConsultaLista';
import './App.css';

function App() {
  const [refreshPacientes, setRefreshPacientes] = useState(0);
  const [refreshConsultas, setRefreshConsultas] = useState(0);

  const hoje = new Date().toLocaleDateString('pt-BR', {
    weekday: 'long', day: '2-digit', month: 'long',
  });

  return (
    <div className="container">
      <header className="top">
        <div className="brand-mark">
          <span className="dot" />
          <div>
            <h1>Medsafe Agenda</h1>
            <p>Gestão de pacientes e consultas — atenção básica</p>
          </div>
        </div>
        <span className="today-tag">{hoje}</span>
      </header>

      <section>
        <p className="section-label">Pacientes</p>
        <div className="layout">
          <PacienteForm onPacienteCriado={() => setRefreshPacientes((n) => n + 1)} />
          <PacienteLista refresh={refreshPacientes} />
        </div>
      </section>

      <section>
        <p className="section-label">Agenda de consultas</p>
        <div className="layout">
          <ConsultaForm onConsultaCriada={() => setRefreshConsultas((n) => n + 1)} />
          <ConsultaLista refresh={refreshConsultas} />
        </div>
      </section>
    </div>
  );
}

export default App;