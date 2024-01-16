import '../styles/Modal.css'
import Profile from "./Profile";

const ProfileModal = ({closeModal}) => {

    return ( 
        <div className="profile-modal">
            <div className="modal-background">
                <div className="modal-container">
                    <button onClick={() => closeModal(false)} className="close-modal-button">X</button>
                    <div className="profile">
                        <Profile/>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default ProfileModal;