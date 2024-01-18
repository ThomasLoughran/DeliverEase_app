// import '../styles/ProfileModal.css';
import Profile from "./Profile";
import '../styles/Modal.css'
import { useState, useEffect } from 'react';
import { useUser } from '../contexts/UserContext';

const ProfileModal = ({closeModal}) => {

    const [modalOpen, setModalOpen] = useState(false); // Add this line for modal state

    //to add/remove class when modalOpen changes
    useEffect(() => {
        if (modalOpen) {
            document.body.classList.add('modal-opened');
        } else {
            document.body.classList.remove('modal-opened');
        }

        //to remove the class when the component unmounts
        return () => {
            document.body.classList.remove('modal-opened');
        };
    }, [modalOpen]);


    return ( 
        <div className="profile-modal" >
            <div className="profile-modal-background">
                <div className="profile-modal-container">
                    <div className="profile">
                        <Profile 
                        closeModal={closeModal}/>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default ProfileModal;