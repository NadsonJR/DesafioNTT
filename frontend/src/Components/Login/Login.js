import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

function Login({ onSwitchToRegister }) {
  const [loginData, setLoginData] = useState({
    email: '',
    senha: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData({
      ...loginData,
      [name]: value
    });
  };

  const handleRegisterClick = () => {
    navigate('/register');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:5550/pessoas/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(loginData)
    })
      .then(response => {
        if (response.ok) {
          toast.success('Login realizado com sucesso');
          setTimeout(() => {
            navigate('/home');
          }, 2000); // Delay of 2 seconds before redirecting
        } else {
          throw new Error('Verifique se o login e a senha estão corretos');
        }
      })
      .catch(error => {
        toast.error('Erro ao realizar login: ' + error.message);
      });
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>E-mail:</label>
          <input type="text" name="email" value={loginData.email} onChange={handleChange} />
        </div>
        <div>
          <label>Senha:</label>
          <input type="password" name="senha" value={loginData.senha} onChange={handleChange} />
        </div>
        <button type="submit">Login</button>
      </form>
      <p>Não tem uma conta?</p>
      <button onClick={handleRegisterClick}>Cadastrar</button>
      <ToastContainer />
    </div>
  );
}

export default Login;