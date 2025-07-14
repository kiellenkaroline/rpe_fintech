import React, { useEffect, useState } from 'react'
import api from '../api/api';


export default function Faturas() {
    const [faturas, setFaturas] = useState([]);
    const [atrasadas, setAtrasadas] = useState([]);
    const [idPagamento, setIdPagamento] = useState([]);

    useEffect(() => {
        carregarFaturas();
    }, []);

    const carregarFaturas = async () => {
        try {
            const todas = await api.get('/faturas');
            const atrasadas = await api.get('/faturas/atrasadas');
            setFaturas(todas.data);
            setAtrasadas(atrasadas.data);
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
            case 'p': return 'Paga';
        }
    };

    return (
        <div className="container my-5">
            <h1 className="mb-4">Faturas</h1>

            <div className="mb-4 d-flex align-items-center gap-2">
                <input
                    type="text"
                    className="form-control w-25"
                    placeholder="ID da Fatura"
                    value={idPagamento}
                    onChange={(e) => setIdPagamento(e.target.value)}
                />
                <button className="btn btn-primary" onClick={pagarFatura}>
                    Pagar Fatura
                </button>
            </div>

            <div className="mb-5">
                <h3>Todas as Faturas</h3>
                <table className="table table-bordered">
                    <thead className="table-light">
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

            <div>
                <h3>Faturas Atrasadas</h3>
                {atrasadas.length === 0 ? (
                    <p className="text-success">Nenhuma fatura em atraso.</p>
                ) : (
                    <table className="table table-bordered">
                        <thead className="table-danger">
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
                )}
            </div>
        </div>
    );
}