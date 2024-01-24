import './App.css';
import UserProvider from './contexts/UserContext';
import Routes from './routes';

function App() {

  return (
    <>
      <UserProvider>
        <Routes />
      </UserProvider>

    </>
  );
}

export default App;
