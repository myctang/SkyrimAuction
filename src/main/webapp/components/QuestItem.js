import React from 'react'
import {ListItem} from 'react-toolbox/lib/list';
import Dialog from 'react-toolbox/lib/dialog';
import {connect} from "react-redux";
import * as questsActions from "../actions/questsActions";
import * as userActions from "../actions/userActions";
import {bindActionCreators} from "redux";
import $ from 'jquery';

export class QuestItem extends React.Component {
    constructor(props){
        super(props);
        this.takeQuest = this.takeQuest.bind(this);
    }

    state = {
        active: false
    };

    handleToggle = () => {
        this.setState({active: !this.state.active});
    };

    takeQuest = () => {
        console.log("take quest");
        debugger;
        this.props.questActions.takeQuest($.cookie("access_token"), this.props.id);
        this.props.userActions.getUserInfo($.cookie("access_token"));
        this.props.questActions.getQuestsList();
        this.handleToggle();
    };

    render() {
        let actions = [
            { label: "Взять квест", onClick: this.takeQuest, disabled: !this.props.activeQuest, raised: true, primary: true },
            { label: "Закрыть", onClick: this.handleToggle, raised: true, primary: true }
        ];
        console.log("quest item");
        debugger;
        return (
            <div>
                <ListItem
                    caption={this.props.name}
                    legend={this.props.duration}
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
                    Продолжительность: {this.props.duration} <br/>
                    Награда: {this.props.rewards}<br/>
                </Dialog>
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps");
    return {
        activeQuest: state.getQuestsList.activeQuest
    }
}

function mapDispatchToProps(dispatch) {
    return {
        questActions: bindActionCreators(questsActions, dispatch),
        userActions: bindActionCreators(userActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(QuestItem)