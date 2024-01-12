import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";

const Routes = () => {

    const routesForPublic = [

        {
            path: "/login",
            element: <LoginPage/>
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