import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";
import LogoutPage from "../pages/LogoutPage";
import { useUser } from "../contexts/UserContext"
import HomePage from "../pages/HomePage";
import NavBar from "../components/NavBar";
import Profile from "../components/Profile";


const Routes = () => {


    const LoggedInLayout = () => {
        return (
            <div className="main">
                <NavBar/>
                <div className="container">
                    <div className="profileContainer">
                        <Profile/>
                    </div>
                    <div className="contentContainer">
                        <Outlet/>
                    </div>
                </div>
            </div>
        )
    }





    const { user } = useUser();

    const routesForPublic = [

        {
            path: "/about-us",
            element: <p>About-us</p>
        },
        {
            path: "/home",
            element: <p>Not logged in user home page</p>
        },


    ];

    const routesForLoggedInOnly = [
        {
            path: "/",
            element: <ProtectedRoute />,
            children: [
                {
                    path: "/",
                    element: <Dashboard />
                },
                {
                    path: "/test",
                    element: <p>test</p>
                },
                {
                    path: "/logout",
                    element: <LogoutPage />
                }
            ]
        }
    ]

    const routesForNotLoggedInOnly = [
        {
            path: "/",
            element: <HomePage/>,
        },
        {
            path: "/login",
            element: <LoginPage />
        }
    ]




    const router = createBrowserRouter([
        ...routesForPublic,
        ...(!user ? routesForNotLoggedInOnly : []),
        ...routesForLoggedInOnly,
    ])



    return (
        <RouterProvider router={router} />
    );
}

export default Routes;