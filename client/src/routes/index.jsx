import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";
import LogoutPage from "../pages/LogoutPage";

const Routes = () => {

    const routesForPublic = [

        {
            path: "/login",
            element: <LoginPage/>
        },
        {
            path: "/home",
            element: <p>Not logged in user home page</p>
        }

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




    const router = createBrowserRouter([
        ...routesForPublic,
        ...routesForLoggedInOnly,
    ])



    return ( 
        <RouterProvider router={router} />
    );
}

export default Routes;