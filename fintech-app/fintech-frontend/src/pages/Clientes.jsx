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
            const res = await api.get('/clientes');
            setClientes(res.data);
        } catch (error) {
            console.error('Erro ao buscar clientes:', error);
        }
    };

    const buscarBloqueados = async () => {
        try {
            const res = await api.get('/clientes/bloqueados'); // Endpoint esperado no backend
            setBloqueados(res.data);
        } catch (error) {
            console.error('Erro ao buscar clientes bloqueados:', error);
        }
    };

    const salvarCliente = async () => {
        try {
            if (editandoId) {
                await api.put(`/clientes/${editandoId}`, clienteForm);
                setEditandoId(null);
            } else {
                await api.post('/clientes', clienteForm);
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
        <div style={{ padding: '2rem' }}>
            <h1>Gerenciamento de Clientes</h1>

            <div style={{ marginBottom: '1rem' }}>
                <input
                    type="text"
                    placeholder="Nome"
                    value={clienteForm.nome}
                    onChange={(e) => setClienteForm({ ...clienteForm, nome: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="CPF"
                    value={clienteForm.cpf}
                    onChange={(e) => setClienteForm({ ...clienteForm, cpf: e.target.value })}
                />
                <button onClick={salvarCliente}>
                    {editandoId ? 'Atualizar Cliente' : 'Adicionar Cliente'}
                </button>
            </div>

            <h2>Lista de Clientes</h2>
            <ul>
                {clientes.map((cliente) => (
                    <li key={cliente.id}>
                        {cliente.nome} - CPF: {cliente.cpf}
                        <button onClick={() => editarCliente(cliente)} style={{ marginLeft: '10px' }}>
                            Editar
                        </button>
                    </li>
                ))}
            </ul>

            <h2>Clientes Bloqueados</h2>
            {bloqueados.length > 0 ? (
                <ul>
                    {bloqueados.map((cliente) => (
                        <li key={cliente.id}>
                            {cliente.nome} - CPF: {cliente.cpf}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>Nenhum cliente bloqueado.</p>
            )}
        </div>
    );
}
