import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";
import LogoutPage from "../pages/LogoutPage";
import { useUser }  from "../contexts/UserContext"


const Routes = () => {


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
            element: <ProtectedRoute/>,
            children: [
                {
                    path: "/",
                    element: <Dashboard/>
                },
                {
                    path: "/test",
                    element: <p>test</p>
                },
                {
                    path: "/logout",
                    element: <LogoutPage/>
                }
            ]
        }
    ]

    const routesForNotLoggedInOnly = [
        {
            path: "/",
            element: <div>Home Page</div>,
        },
        {
            path: "/login",
            element: <LoginPage/>
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