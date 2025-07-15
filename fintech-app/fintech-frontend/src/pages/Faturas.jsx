import React, { useEffect, useState } from 'react';
import api from '../api/api';

export default function Faturas() {
    const [faturas, setFaturas] = useState([]);
    const [atrasadas, setAtrasadas] = useState([]);
    const [idPagamento, setIdPagamento] = useState('');

    useEffect(() => {
        carregarFaturas();
    }, []);

    const carregarFaturas = async () => {
        try {
            const todas = await api.get('/faturas');
            const atrasadas = await api.get('/faturas/atrasadas');
            setFaturas(todas.data);
            console.log('Todas as faturas:', todas.data);
            setAtrasadas(atrasadas.data);
            console.log('Faturas atrasadas:', atrasadas.data);
        } catch (error) {
            console.error('Erro ao carregar faturas:', error);
        }
    };

    const pagarFatura = async () => {
        if (!idPagamento) return alert('Informe o ID da fatura.');

        try {
            await api.put(`/faturas/${idPagamento}/pagamento`);
            alert(`Fatura ${idPagamento} paga com sucesso!`);
            setIdPagamento('');
            carregarFaturas();
        } catch (error) {
            alert(error.response?.data?.message || 'Erro ao pagar fatura');
            console.error('Erro ao pagar fatura:', error);
        }
    };

    const statusFormatado = (status) => {
        switch (status) {
            case 'B': return 'Em aberto';
            case 'A': return 'Atrasada';
            case 'P': return 'Paga'; // corrigido: era 'p'
            default: return status;
        }
    };

    return (
        <div className="container py-5">
            <h1 className="mb-4 text-center text-primary">Faturas</h1>

            <div className="card p-4 shadow-sm mb-5">
                <h5 className="mb-3">Pagamento de Fatura</h5>
                <div className="d-flex flex-wrap gap-2 align-items-center">
                    <input
                        type="text"
                        className="form-control w-25"
                        placeholder="ID da Fatura"
                        value={idPagamento}
                        onChange={(e) => setIdPagamento(e.target.value)}
                    />
                    <button className="btn btn-outline-primary" onClick={pagarFatura}>
                        Pagar Fatura
                    </button>
                </div>
            </div>

            <div className="mb-5">
                <h4 className="mb-3">📄 Todas as Faturas</h4>
                <div className="table-responsive">
                    <table className="table table-hover table-bordered align-middle">
                        <thead className="table-primary">
                            <tr>
                                <th>ID</th>
                                <th>Cliente</th>
                                <th>Valor</th>
                                <th>Vencimento</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {faturas.map((f) => (
                                <tr key={f.id}>
                                    <td>{f.id}</td>
                                    <td>{f.cliente?.id}</td>
                                    <td>R$ {f.valor.toFixed(2)}</td>
                                    <td>{f.dataVencimento}</td>
                                    <td>{statusFormatado(f.status)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>

            <div>
                <h4 className="mb-3">⚠️ Faturas Atrasadas</h4>
                {atrasadas.length === 0 ? (
                    <div className="alert alert-success">Nenhuma fatura em atraso.</div>
                ) : (
                    <div className="table-responsive">
                        <table className="table table-bordered table-striped table-danger">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Cliente</th>
                                    <th>Valor</th>
                                    <th>Vencimento</th>
                                </tr>
                            </thead>
                            <tbody>
                                {atrasadas.map((f) => (
                                    <tr key={f.id}>
                                        <td>{f.id}</td>
                                        <td>{f.cliente?.id}</td>
                                        <td>R$ {f.valor.toFixed(2)}</td>
                                        <td>{f.dataVencimento}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
}