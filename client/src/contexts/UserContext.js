import { createContext, useContext, useState } from "react";



const UserContext = createContext();

const UserProvider = ({ children }) => {



    const [user, setUser] = useState(null);


    const loginUser = (userData) => {
        setUser(userData);
    };

    const logoutUser = () => {
        setUser(null);
    };



    return ( 
        <UserContext.Provider value={{user, loginUser, logoutUser}}>
            {children}
        </UserContext.Provider>
    );

};
export const useUser = () => {
    return useContext(UserContext)
}

export default UserProvider;