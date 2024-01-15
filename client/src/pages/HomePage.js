import { Navigate, useNavigate } from "react-router-dom";
import '../styles/HomePage.css'

const HomePage = () => {


    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/login");
    };



    return ( 
        <div className="homepage-container">
            <h1 className="homepage-title">DelieverEase</h1>
            <p className="homepage-slogan">Where every mile feels like a smile!</p>
            <button onClick={handleClick} className="homepage-login-button">Login</button>
        </div>



    );
}

export default HomePage;