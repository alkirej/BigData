from airflow import DAG
from airflow.operators.bash_operator import BashOperator
from datetime import datetime, timedelta

# Following are defaults which can be overridden later on
default_args: dict = {
    'owner': 'jeff',
    'depends_on_past': False,
    'start_date': datetime(2020,1,30),
    'email': ['jeffery.alkire@enhanceit.us'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=1),
}

dag:DAG = DAG( 'project_dag',
           default_args=default_args,
           schedule_interval=timedelta(hours=1)
         )

# operators (executors) for each task we must run named according to the BigData assignment numbers.
project_2 = BashOperator( task_id='project_2',
                          bash_command='/home/jeff/BigData/002-NetCat/run_howto.sh ',
                          dag=dag
                        )

project_3 = BashOperator( task_id='project_3',
                          bash_command='/home/jeff/BigData/003-SkillsTest/run.sh ',
                          dag=dag
                        )

project_4 = BashOperator( task_id='project_4',
                          bash_command='/home/jeff/BigData/004-WordCount/python/run ',
                          dag=dag
                        )

project_8 = BashOperator( task_id='project_8',
                          bash_command='/home/jeff/BigData/008-WordCountInScala/run ',
                          dag=dag
                        )

project_9 = BashOperator( task_id='project_9',
                          bash_command='/home/jeff/BigData/009-SkillsTestInScala/run.sh ',
                          dag=dag
                        )

project_10 = BashOperator( task_id='project_10',
                           bash_command='/home/jeff/BigData/010-FlumeNetcat/run_howto.sh ',
                           dag=dag
                         )

project_11 = BashOperator( task_id='project_11',
                           bash_command='/home/jeff/BigData/011-FlumeExecSeq/run_howto.sh ',
                           dag=dag
                         )

project_14 = BashOperator( task_id='project_14',
                           bash_command='/home/jeff/BigData/012-HadoopCreateDir/hadoopDirCreate.sh ',
                           dag=dag
                         )

# FIRST DEPENDENCY SET 2 >> 3 >> 4 (SET A)
project_2.set_downstream( project_3 )
project_3.set_downstream( project_4 )

# SECOND DEPENDENCY SET 8 >> 9 >> 10  ( SET B )
project_8.set_downstream( project_9 )
project_9.set_downstream( project_10 )

# THIRD DEPENDENCY SET 11 >> 14  ( SET C )
project_11.set_downstream( project_14 )

# FOURTH DEPENDENCY SET  [A,B] >> C
project_10.set_downstream( project_11 )
project_4.set_downstream( project_11 )