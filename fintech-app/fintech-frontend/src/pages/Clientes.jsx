import { useEffect, useState } from 'react';
import api from '../api/api';

export default function Clientes() {
    const [clientes, setClientes] = useState([]);
    const [bloqueados, setBloqueados] = useState([]);
    const [clienteForm, setClienteForm] = useState({ nome: '', cpf: '' });
    const [editandoId, setEditandoId] = useState(null);

    useEffect(() => {
        buscarClientes();
        buscarBloqueados();
    }, []);

    const buscarClientes = async () => {
        try {
            const res = await api.get('/api/clientes');
            setClientes(res.data);
        } catch (error) {
            console.error('Erro ao buscar clientes:', error);
        }
    };

    const buscarBloqueados = async () => {
        try {
            const res = await api.get('/api/clientes/bloqueados');
            setBloqueados(res.data);
        } catch (error) {
            console.error('Erro ao buscar clientes bloqueados:', error);
        }
    };

    const salvarCliente = async () => {
        try {
            if (editandoId) {
                await api.put(`/api/clientes/${editandoId}`, clienteForm);
                setEditandoId(null);
            } else {
                await api.post('/api/clientes', clienteForm);
            }
            setClienteForm({ nome: '', cpf: '' });
            buscarClientes();
            buscarBloqueados();
        } catch (error) {
            console.error('Erro ao salvar cliente:', error);
        }
    };

    const editarCliente = (cliente) => {
        setClienteForm({ nome: cliente.nome, cpf: cliente.cpf });
        setEditandoId(cliente.id);
    };

    return (
        <div style={{ padding: '2rem', fontFamily: 'Arial, sans-serif', background: '#f0f4f8', color: '#1a1a1a', minHeight: '100vh' }}>
            <h1 style={{ color: '#003366', textAlign: 'center' }}>Gerenciamento de Clientes</h1>

            <div style={{ maxWidth: '600px', margin: '2rem auto', background: '#ffffff', padding: '1.5rem', borderRadius: '12px', boxShadow: '0 4px 8px rgba(0,0,0,0.1)' }}>
                <h2 style={{ color: '#003366' }}>{editandoId ? 'Editar Cliente' : 'Cadastrar Cliente'}</h2>
                <input
                    type="text"
                    placeholder="Nome"
                    value={clienteForm.nome}
                    onChange={(e) => setClienteForm({ ...clienteForm, nome: e.target.value })}
                    style={inputStyle}
                />
                <input
                    type="text"
                    placeholder="CPF"
                    value={clienteForm.cpf}
                    onChange={(e) => setClienteForm({ ...clienteForm, cpf: e.target.value })}
                    style={inputStyle}
                />
                <button onClick={salvarCliente} style={buttonStyle}>
                    {editandoId ? 'Atualizar Cliente' : 'Adicionar Cliente'}
                </button>
            </div>

            <div style={sectionStyle}>
                <h2 style={sectionTitleStyle}>Lista de Clientes</h2>
                {clientes.map((cliente) => (
                    <div key={cliente.id} style={cardStyle}>
                        <p><strong>{cliente.nome}</strong></p>
                        <p>CPF: {cliente.cpf}</p>
                        <button onClick={() => editarCliente(cliente)} style={smallButtonStyle}>Editar</button>
                    </div>
                ))}
            </div>

            <div style={sectionStyle}>
                <h2 style={sectionTitleStyle}>Clientes Bloqueados</h2>
                {bloqueados.length > 0 ? (
                    bloqueados.map((cliente) => (
                        <div key={cliente.id} style={{ ...cardStyle, background: '#ffeeee' }}>
                            <p><strong>{cliente.nome}</strong></p>
                            <p>CPF: {cliente.cpf}</p>
                        </div>
                    ))
                ) : (
                    <p style={{ color: '#666' }}>Nenhum cliente bloqueado.</p>
                )}
            </div>
        </div>
    );
}

const inputStyle = {
    display: 'block',
    width: '100%',
    padding: '0.6rem',
    marginBottom: '1rem',
    borderRadius: '8px',
    border: '1px solid #ccc',
    outlineColor: '#0077cc',
    fontSize: '1rem',
};

const buttonStyle = {
    backgroundColor: '#0077cc',
    color: 'white',
    padding: '0.7rem 1.2rem',
    border: 'none',
    borderRadius: '8px',
    cursor: 'pointer',
    fontWeight: 'bold',
};

const smallButtonStyle = {
    ...buttonStyle,
    padding: '0.4rem 0.8rem',
    marginTop: '0.5rem',
    fontSize: '0.9rem',
};

const sectionStyle = {
    maxWidth: '700px',
    margin: '2rem auto',
};

const sectionTitleStyle = {
    color: '#003366',
    borderBottom: '2px solid #0077cc',
    paddingBottom: '0.5rem',
    marginBottom: '1rem',
};

const cardStyle = {
    background: '#ffffff',
    borderRadius: '10px',
    padding: '1rem',
    marginBottom: '1rem',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
};
