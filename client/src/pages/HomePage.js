import { useNavigate } from "react-router-dom";
import '../styles/HomePage.css';
import lightLogo from '../assets/light-mode-logo.png'; //altered the dark-logo and light logo sizes.

const HomePage = () => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/login");
    };

    return (
        <div className="homepage-container">
            <img className="homepage-logo" src={lightLogo} />
            <p className="homepage-slogan">Where every mile feels like a smile!</p>
            <button onClick={handleClick} className="homepage-login-button">Login</button>
        </div>
    );
}

export default HomePage;