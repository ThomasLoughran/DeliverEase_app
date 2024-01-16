import AddDriverForm from "./ManagerDashboardComponents/AddDriverForm";
import MessageList from "./ManagerDashboardComponents/MessageList";
import '../styles/Modal.css'

const MessageModal = ({closeModal}) => {

    return ( 
        <div className="message-modal">
            <div className="message-modal-background">
                <div className="message-modal-container">
                    <button onClick={() => closeModal(false)} className="close-message-modal-button">X</button>
                    <div className="MessageList-container">
                        <MessageList/>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default MessageModal;