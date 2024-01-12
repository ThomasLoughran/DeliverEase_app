
import './App.css';
import LoginPage from './pages/LoginPage';
import UserProvider from './contexts/UserContext';
import Routes from './routes';


function App() {
  return (
    <>
    <UserProvider>
      <Routes/>
    </UserProvider>
      
    </>
  );
}

export default App;
