import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

function Register({ onRegister, onBack }) {
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    dataNascimento: '',
    email: '',
    telefone: '',
    senha: '',
    cep: '',
    logradouro: '',
    bairro: '',
    localidade: '',
    uf: '',
    estado: ''
  });

  const [emailError, setEmailError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setFormData({
      ...formData,
      email: newEmail
    });

    fetch(`http://localhost:5550/pessoas/check-email?email=${newEmail}`)
      .then(response => response.text().then(message => ({ status: response.status, message })))
      .then(({ status, message }) => {
        if (status === 409) {
          setEmailError(message);
        } else {
          setEmailError('');
        }
      })
      .catch(error => {
        console.error('Error checking email:', error);
      });
  };

  const handleCepSearch = () => {
    fetch(`http://localhost:5550/endereco/cep/${formData.cep}`)
      .then(response => response.json())
      .then(data => {
        setFormData({
          ...formData,
          logradouro: data.logradouro,
          bairro: data.bairro,
          localidade: data.localidade,
          uf: data.uf,
          estado: data.estado
        });
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const handleBackClick = () => {
      navigate('/');
    };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:5550/pessoas/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
      .then(response => response.json())
      .then(pessoaData => {
        console.log('Pessoa cadastrada com sucesso:', pessoaData);
        const enderecoData = {
          cep: formData.cep,
          logradouro: formData.logradouro,
          bairro: formData.bairro,
          localidade: formData.localidade,
          uf: formData.uf,
          estado: formData.estado,
          pessoaId: pessoaData.id // Use the returned id from pessoa creation
        };

        return fetch('http://localhost:5550/endereco/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(enderecoData)
        });
      })
      .then(response => response.json())
      .then(data => {
        console.log('Endereço cadastrado com sucesso:', data);
        toast.success('Cadastro realizado com sucesso!');
        setTimeout(() => {
          navigate('/'); // Redirect to login page
        }, 2000); // Delay of 2 seconds before redirecting
      })
      .catch((error) => {
        console.error('Erro ao cadastrar:', error);
        toast.error('Erro ao realizar o cadastro.');
      });
  };

  return (
    <div>
      <h2>Cadastro</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nome:</label>
          <input required type="text" name="nome" value={formData.nome} onChange={handleChange} minLength="3" />
        </div>
        <div>
          <label>CPF:</label>
          <input required type="text" name="cpf" value={formData.cpf} onChange={handleChange} minLength="11" />
        </div>
        <div>
          <label>Data de Nascimento:</label>
          <input required type="date" name="dataNascimento" value={formData.dataNascimento} onChange={handleChange} />
        </div>
        <div>
          <label>Email:</label>
          <input type="email" name="email" value={formData.email} onChange={handleEmailChange} />
          {emailError && <p style={{ color: 'red' }}>{emailError}</p>}
        </div>
        <div>
          <label>Telefone:</label>
          <input required type="text" name="telefone" value={formData.telefone} onChange={handleChange} minLength="10" />
        </div>
        <div>
          <label>Senha:</label>
          <input required type="password" name="senha" value={formData.senha} onChange={handleChange} minLength="6" />
        </div>
        <div>
          <label>Cep:</label>
          <input type="text" name="cep" value={formData.cep} onChange={handleChange} minLength="8" />
          <button type="button" onClick={handleCepSearch}>Buscar Cep</button>
        </div>
        <div>
          <label>Logradouro:</label>
          <input required type="text" name="logradouro" value={formData.logradouro} onChange={handleChange} />
        </div>
        <div>
          <label>Bairro:</label>
          <input required type="text" name="bairro" value={formData.bairro} onChange={handleChange} />
        </div>
        <div>
          <label>Localidade:</label>
          <input required type="text" name="localidade" value={formData.localidade} onChange={handleChange} />
        </div>
        <div>
          <label>UF:</label>
          <input required type="text" name="uf" value={formData.uf} onChange={handleChange} />
        </div>
        <div>
          <label>Estado:</label>
          <input required type="text" name="estado" value={formData.estado} onChange={handleChange} />
        </div>
        <button type="submit">Cadastrar</button>
        <button type="button" onClick={handleBackClick}>Voltar</button>
      </form>
      <ToastContainer />
    </div>
  );
}

export default Register;