<template>
  <div>
    <h1 style="float: left">{{ currentTime | formatDate2}}</h1>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-button
          class="el-icon-refresh"
          type="text"
          @click="refreshData">刷新
        </el-button>
      </el-form-item>
      <el-form-item>
        <router-link to="/" active-class="router-active">
          导航栏
        </router-link>
      </el-form-item>
    </el-form>

    <el-table
      :data="tableData"
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        label="编号">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="任务内容">
        <template slot-scope="scope">
          <span>{{ scope.row.task.content }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="分值">
        <template slot-scope="scope">
          <span>{{ scope.row.task.score}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="得分">
        <template slot-scope="scope">
          <span>{{ scope.row.score}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="是否完成">
        <template slot-scope="scope">
          <el-button size="medium" icon="el-icon-circle-check"
                     v-if="scope.row.finish===false"
                     @click="changeFinish(scope.$index, scope.row)">
          </el-button>
          <el-button v-else icon="el-icon-success" size="medium"
                     @click="changeUnFinish(scope.$index, scope.row)">
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  // import {formatDate} from "../utils/formatDate.js";//路径要注意下
  export default {
    data() {
      return {
        taskRecordForm: {
          id: '',
          task: {
            id: '',
            content: '',
            score: '',
            status: '',
            description: '',
            cycleTypeId: ''
          },
          finish: '',
          score: ''
        },
        rules: {
          userName: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur'}
          ],
          userAddress: [
            {required: true, message: '请输入住址', trigger: 'blur'},
            {min: 5, message: '长度大于 5 个字符', trigger: 'blur'}
          ],
        },
        tableData: [],
        search: '',
        dialogVisible: false,
        dialogUpdate: false,
        pageSize: 10,
        currentPage: 1,
        total: 20,
        disablePage: false,
        timer: "",//定义一个定时器的变量
        currentTime: new Date(), // 获取当前时间
      }
    },
    methods: {
      changeFinish(index, row) {
        console.log(index, row);
        this.axios({
          method: 'patch',
          url: '/v1/record/task/' + row.id + '/true'
        }).then(response => {
          this.axios.get('/v1/record/task/today').then(response => {
            this.tableData = response.data;
          }).catch(error => {
            console.log(error);
          });
          this.$message({
            type: 'success',
            message: '恭喜完成!'
          });
          console.log(response);
        })
      },
      changeUnFinish(index, row) {
        console.log(index, row);
        this.axios({
          method: 'patch',
          url: '/v1/record/task/' + row.id + '/false'
        }).then(response => {
          this.axios.get('/v1/record/task/today').then(response => {
            this.tableData = response.data;
          }).catch(error => {
            console.log(error);
          });
          this.$message({
            type: 'success',
            message: '还需要加油哦！'
          });
          console.log(response);
        })
      },
      handleEdit(index, row) {
        this.dialogUpdate = true;
        this.taskForm = Object.assign({}, row); //这句是关键！！！
      },
      handleDelete(index, row) {
        console.log(index, row);
        this.$confirm('删除操作, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios({
            method: 'delete',
            url: '/v1/task/' + row.id
          }).then(response => {
            this.axios.delete('/v1/task/' + row.id).then(response => {
              this.tableData = response.data;
            }).catch(error => {
              console.log(error);
            });
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log(response);
            location.reload();
          }).catch(error => {
            console.log(error);
          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      handleCurrentChange() {
        console.log(`当前页: ${this.currentPage}`);
        let postData = this.qs.stringify({
          page: this.currentPage
        });
        this.axios({
          method: 'post',
          url: '/page',
          data: postData
        }).then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      cancel() {
        this.dialogUpdate = false;
        this.dialogVisible = false;
        this.emptyTaskData();
      },
      emptyTaskData() {
        this.taskForm = {
          id: '',
          content: '',
          description: '',
          cycleTypeId: '',
          status: '',
          score: '',
          updateTime: '',
          createTime: ''
        }
      },
      addTask() {
        let postData = JSON.stringify({
          content: this.taskForm.content,
          description: this.taskForm.description,
          cycleTypeId: this.taskForm.cycleTypeId,
          status: this.taskForm.status,
          score: this.taskForm.score
        });
        this.axios({
          method: 'post',
          url: '/v1/task',
          data: postData
        }).then(response => {
          this.axios.get('/v1/task').then(response => {
            this.tableData = response.data;
            this.currentPage = 1;
            this.$message({
              type: 'success',
              message: '已添加!'
            });
          }).catch(error => {
            console.log(error);
          });
          this.getPages();
          this.dialogVisible = false
          console.log(response);
          location.reload();
        }).catch(error => {
          console.log(error);
        });
      },
      updateTask() {
        let postData = this.qs.stringify({
          id: this.taskForm.id,
          content: this.taskForm.content,
          description: this.taskForm.description,
          cycleTypeId: this.taskForm.cycleTypeId,
          status: this.taskForm.status,
          score: this.taskForm.score
        });
        this.axios({
          method: 'put',
          url: '/v1/task',
          data: postData
        }).then(response => {
          this.handleCurrentChange();
          this.cancel();
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          console.log(response);
          location.reload();
        }).catch(error => {
          this.$message({
            type: 'success',
            message: '更新失败!'
          });
          console.log(error);
        });
      },
      onSearch() {
        let postData = this.qs.stringify({
          userName: this.search
        });
        this.axios({
          method: 'post',
          url: '/ListByName',
          data: postData
        }).then(response => {
          this.tableData = response.data;
          this.disablePage = true;
        }).catch(error => {
          console.log(error);
        });
      },
      getPages() {
        this.axios.get('/v1/task').then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
      },
      refreshData() {
        location.reload();
      },
    },

    filters: {
      formatDate2: function (value) {// 时间戳转换日期格式方法
        if (value == null) {
          return '';
        } else {
          let date = new Date(value);
          let y = date.getFullYear();// 年
          let MM = date.getMonth() + 1;// 月
          MM = MM < 10 ? ('0' + MM) : MM;
          let d = date.getDate();// 日
          d = d < 10 ? ('0' + d) : d;
          return y + '-' + MM + '-' + d;
        }
      }
    },

    created() {
      this.axios.get('/v1/record/task/today').then(response => {
        this.tableData = response.data;
      }).catch(error => {
        console.log(error);
      });

    }
  }
</script>
<style scoped>
  .search_name {
    width: 200px;
  }

  .pages {
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
