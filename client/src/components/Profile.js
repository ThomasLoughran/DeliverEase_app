import { useState } from 'react';
import { useUser } from '../contexts/UserContext';
import '../styles/Profile.css'; 
import { Link } from 'react-router-dom';

// TO-DO:
//save button to update user password 
//add current password verification before user can update password
//password fields to clear on save/submit/update
//css: bring modal to center of screen 
//css: position profile and message icon in column
//css: resolve impact on messageIcon on profile-expand ✅


const Profile = () => {

    const { user } = useUser();
    const [showMore, setShowMore] = useState(false);
    const [employeeForm, setEmployeeForm] = useState({
        id: `${user.id}`,
        oldPassword: "",
        newPassword: "",
    });

    //handle form change
    const handleEmployeeFormChange = (e) => {
        const {name, value} = e.target;
        setEmployeeForm((previousData) => ({
            ...previousData, [name]: value, 
        }))
    }
    
    //handle form submit
    const handleUpdateEmployeeForm = async (e) => {
        e.preventDefault();
    
    
            // if (employeeForm.newPassword !== employeeForm.password ) {
            //     alert('Passwords do not match.');
            //     return;
            // }        
    
            
            try {
                const response = await fetch('http://localhost:8080/employees/update-password', {
                    method: "PATCH",
                    headers: {"Content-Type": "application/json"}, 
                    body: JSON.stringify(employeeForm)
                })

                if (!response.ok) {
                    throw new Error(`Failed to save new password: ${response.status}. Please try again.`)
                } else {
                    setEmployeeForm({
                        oldPassword: "",
                        newPassword: "",
                    })
                }

            } catch (error){
                console.error(error);
            }

            alert('Passwords successfully changed');

    }

    return (
        <div className="profile-content">
            <h2>My Profile:</h2>
            {user.role === 'MANAGER' && (
                <>
                    <p>Name: {user.name}</p>
                    <p>ID: {user.id}</p>
                </>
            )}
            {user.role === 'DRIVER' && (
                <>
                    <p>Name: {user.name}</p>
                    <p>ID: {user.id}</p>
                    <p>Van name: {user.vanName}</p>
                </>
            )}

        {/* <Link to="" ><button id="update-details-button"> <b>Update details</b> </button></Link>     
         */}
            {showMore &&  (
            <form id="update-employee-form" onSubmit={handleUpdateEmployeeForm}>
                <label htmlFor="old-password">
                    Old password: 
                </label>
                    <input 
                    placeholder="Please enter your current password"
                    id="old-password"
                    type="password"
                    name="oldPassword"
                    value={employeeForm.oldPassword}
                    onChange={handleEmployeeFormChange}
                    required >
                    </input> 

                <label htmlFor="confirm-change-password">
                    Confirm new password: 
                </label>
                    <input 
                    placeholder="Please add a new password"
                    id="confirm-new-password"
                    type="password"
                    name="newPassword"
                    value={employeeForm.newPassword}
                    onChange={handleEmployeeFormChange}
                    required >
                    </input> 

                <button type="submit" id="save-button" >
                    Save
                </button>
            </form>
            )}

            <button className="update-details-button" 
                onClick={() => setShowMore(!showMore)}>
                {showMore ? 'Cancel' : 'Update details'}
            </button>

        </div>
        

    );
}

export default Profile;
