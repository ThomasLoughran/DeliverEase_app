import AddDriverForm from "./ManagerDashboardComponents/AddDriverForm";
import '../styles/Modal.css'

const Modal = ({closeModal}) => {

    return ( 
        <div className="modal">
            <div className="modal-background">
                <div className="modal-container">
                    <button onClick={() => closeModal(false)} className="close-modal-button">X</button>
                    <div className="driver-form">
                        <AddDriverForm/>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default Modal;