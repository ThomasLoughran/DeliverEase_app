import AddDriverForm from "./ManagerDashboardComponents/AddDriverForm";
import MessageList from "./ManagerDashboardComponents/MessageList";
import '../styles/Modal.css'

const MessageModal = ({closeModal}) => {

    return ( 
        <div className="modal">
            <div className="modal-background">
                <div className="modal-container">
                    <button onClick={() => closeModal(false)} className="close-modal-button">X</button>
                    <div className="MessageList-container">
                        <MessageList/>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default MessageModal;