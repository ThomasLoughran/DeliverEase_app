import { Navigate, useNavigate } from "react-router-dom";

const HomePage = () => {


    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/login");
    };



    return ( 
        <>
        <h1>DelieverEase</h1>
        <p>Where every mile feels like a smile!</p>
        <button onClick={handleClick}>Login</button>
        </>



    );
}
 
export default HomePage;