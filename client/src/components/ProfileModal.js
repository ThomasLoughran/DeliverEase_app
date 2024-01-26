import Profile from "./Profile";
import '../styles/Modal.css'
import { useState, useEffect } from 'react';

const ProfileModal = ({ closeModal }) => {

    const [modalOpen, setModalOpen] = useState(false);

    useEffect(() => {
        if (modalOpen) {
            document.body.classList.add('modal-opened');
        } else {
            document.body.classList.remove('modal-opened');
        }

        return () => {
            document.body.classList.remove('modal-opened');
        };
    }, [modalOpen]);

    return (
        <aside className="profile-modal" >
            <div className="profile-modal-background">
                <div className="profile-modal-container">
                    <div className="profile">
                        <Profile
                            closeModal={closeModal} />
                    </div>
                </div>
            </div>
        </aside>
    );
}

export default ProfileModal;