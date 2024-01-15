import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";
import { DriverProtectedRoute } from "./DriverProtectedRoute";
import { ManagerProtectedRoute } from "./ManagerProtectedRoute";


import LogoutPage from "../pages/LogoutPage";
import { useUser } from "../contexts/UserContext"
import HomePage from "../pages/HomePage";
import NavBar from "../components/NavBar";
import Profile from "../components/Profile";
import DriversListContainer from "../containers/DriversListContainer";
import DistributionCentreListContainer from "../containers/DistributionCentreListContainer";


const Routes = () => {

    // probably should be in a separate file and imported.
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
            path: "/test",
            element: <p>Not logged in user home page</p>
        },
        {
            path: "/home",
            element: <p>Home</p>
        }


    ];

    const routesForLoggedInOnly = [
        {
            path: "/",
            element: <ProtectedRoute />,
            children: [
                {
                    path: "/",
                    element: <LoggedInLayout/>,
                    children: [
                        {
                            path: "/dashboard",
                            element: <p>Hello from home</p>
                        },
                        {
                            path: "/manager",
                            element: <ManagerProtectedRoute/>,
                            children: [
                                {
                                    path:"/manager/drivers",
                                    element: <DriversListContainer/>
                                },
                                {
                                    path:"/manager/distribution-centres",
                                    element: <DistributionCentreListContainer/>
                                }
                            ]
                        },
                        {
                            path: "/driver",
                            element: <DriverProtectedRoute/>,
                            children: [
                                {
                                    path:"/driver/routes",
                                    element: <p>Hello there will be a list of routes here</p>
                                }
                            ]
                        }
                    ]
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