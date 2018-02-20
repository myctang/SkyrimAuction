import React from 'react'
import $ from "jquery";
import * as questActions from "../actions/questsActions";
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {bindActionCreators} from "redux";
import {BuyItem} from "./BuyItem";
import {ListItem} from 'react-toolbox/lib/list';
import Dialog from 'react-toolbox/lib/dialog';

export class CurrentQuest extends React.Component{
    state = {
        active: false
    };

    handleToggle = () => {
        this.setState({active: !this.state.active});
    };

    render() {
        let actions = [
            { label: "Закрыть", onClick: this.handleToggle, raised: true, primary: true }
        ];
        console.log("quest item");
        debugger;
        let now = new Date();
        let min = Math.floor((this.props.user.startOfQuest + this.props.duration * 1000 - now.getTime()) / 60000);
        let sec = Math.floor(((this.props.user.startOfQuest + this.props.duration * 1000 - now.getTime()) / 1000)) - min * 60;
        if (min < 0 || sec < 0) {
            min = 0;
            sec = 0;
        }
        
        return (
            <div className="currentQuest">
                <ListItem
                    caption={this.props.name}
                    onClick={this.handleToggle}
                />
                <Dialog
                    actions={actions}
                    active={this.state.active}
                    onEscKeyDown={this.handleToggle}
                    onOverlayClick={this.handleToggle}
                    title={this.props.name}
                >
                    Название:{this.props.name} <br/>
                    Осталось: <span className="time"><span className="min">{min}</span>:<span className="sec">{sec}</span></span> <br/>
                    Награда: {this.props.rewards}<br/>
                </Dialog>
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps buyItem");
    return {
        user: state.user.user
    }
}

function mapDispatchToProps(dispatch) {
    return {
        buyActions: bindActionCreators(questActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(CurrentQuest))