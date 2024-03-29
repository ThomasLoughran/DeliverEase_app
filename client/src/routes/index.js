import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import LoginPage from "../pages/LoginPage";
import { ProtectedRoute } from "./ProtectedRoute";
import { DriverProtectedRoute } from "./DriverProtectedRoute";
import { ManagerProtectedRoute } from "./ManagerProtectedRoute";

import LogoutPage from "../pages/LogoutPage";
import { useUser } from "../contexts/UserContext"
import HomePage from "../pages/HomePage";
import NavBar from "../components/NavBar";
import DriversListContainer from "../containers/DriversListContainer";
import DistributionCentreListContainer from "../containers/DistributionCentreListContainer";
import ManagerRoutesContainer from "../containers/ManagerRoutesContainer";
import DriverAvailabilityCalendar from "../components/DriverDashboard/DriverAvailabilityCalendar";
import CurrentRouteOrder from "../components/DriverDashboard/CurrentRouteOrder";


const Routes = () => {

    // probably should be in a separate file and imported.
    const LoggedInLayout = () => {
        return (
            <div className="wrapper">
                <NavBar />
                <div className="main">
                    <div className="contentContainer">
                        <Outlet />
                    </div>
                </div>
            </div>
        )
    }

    const { user } = useUser();

    const routesForLoggedInOnly = [
        {
            path: "/",
            element: <ProtectedRoute />,
            children: [
                {
                    path: "/",
                    element: <LoggedInLayout />,
                    children: [
                        {
                            path: "/manager",
                            element: <ManagerProtectedRoute />,
                            children: [
                                {
                                    path: "/manager/routes",
                                    element: <ManagerRoutesContainer />
                                },
                                {
                                    path: "/manager/drivers",
                                    element: <DriversListContainer />
                                },
                                {
                                    path: "/manager/distribution-centres",
                                    element: <DistributionCentreListContainer />
                                },
                            ]
                        },
                        {
                            path: "/driver",
                            element: <DriverProtectedRoute />,
                            children: [
                                {
                                    path: "/driver/routes",
                                    element: <CurrentRouteOrder />
                                },
                                {
                                    path: "/driver/driver-availability",
                                    element: <DriverAvailabilityCalendar />
                                },

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
            element: <HomePage />,
        },
        {
            path: "/login",
            element: <LoginPage />
        }
    ]

    const router = createBrowserRouter([
        ...(!user ? routesForNotLoggedInOnly : []),
        ...routesForLoggedInOnly,
    ])

    return (
        <RouterProvider router={router} />
    );
}

export default Routes;